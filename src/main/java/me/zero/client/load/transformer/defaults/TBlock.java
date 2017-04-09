package me.zero.client.load.transformer.defaults;

import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import me.zero.client.load.transformer.hook.ClassHook;
import me.zero.client.load.transformer.reference.ClassReference;

import java.util.Collection;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.Block;
import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.canCollideCheck;

/**
 * Used to hook the BlockCollisionEvent
 *
 * @since 1.0
 *
 * Created by Brady on 4/8/2017.
 */
@LoadTransformer
public final class TBlock extends Transformer {

    @Override
    public void loadHooks(Collection<ClassHook> hooks) {
        hooks.add(canCollideCheck.createHook(method -> method.insertBefore("{ BlockCollisionEvent event = new BlockCollisionEvent(this); EventManager.post(event); if (event.isCancelled()) return false; }")));
    }

    @Override
    public ClassReference getTargetClass() {
        return Block;
    }
}