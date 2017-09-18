/*
 * Copyright 2017 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.load.mixin.packet.play.server;

import net.minecraft.network.play.server.SPacketCustomSound;
import net.minecraft.util.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 9/10/2017 2:26 AM
 */
@Mixin(SPacketCustomSound.class)
public interface ISPacketCustomSound {

    @Accessor String getSoundName();

    @Accessor void setSoundName(String soundName);

    @Accessor SoundCategory getCategory();

    @Accessor void setCategory(SoundCategory category);

    @Accessor int getX();

    @Accessor void setX(int x);

    @Accessor int getY();

    @Accessor void setY(int y);

    @Accessor int getZ();

    @Accessor void setZ(int z);

    @Accessor float getVolume();

    @Accessor void setVolume(float volume);

    @Accessor float getPitch();

    @Accessor void setPitch(float pitch);
}
