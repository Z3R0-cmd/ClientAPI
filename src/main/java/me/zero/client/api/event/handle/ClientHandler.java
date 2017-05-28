package me.zero.client.api.event.handle;

import me.zero.client.api.ClientAPI;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import me.zero.client.api.event.defaults.*;
import me.zero.client.api.event.defaults.filters.PacketFilter;
import me.zero.alpine.type.EventPriority;
import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.keybind.Keybind;
import me.zero.client.api.util.render.camera.Camera;
import me.zero.client.api.util.render.camera.CameraManager;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketChat;
import org.lwjgl.input.Keyboard;

import java.util.stream.Stream;

import static org.lwjgl.input.Keyboard.KEYBOARD_SIZE;

/**
 * Some basic events that the client uses
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
 */
public final class ClientHandler implements Helper {

    /**
     * A map of all key states
     */
    private final boolean[] keyMap = new boolean[KEYBOARD_SIZE];

    /**
     * Handles camera updates
     */
    @EventHandler
    private final Listener<RenderHudEvent> render2DListener = new Listener<>(event ->
        CameraManager.getInstance().getData().stream().filter(Camera::isVisible).forEach(camera -> camera.updateFramebuffer(event.getPartialTicks())),
            EventPriority.LOWEST);

    /**
     * Handles keybinds
     */
    @EventHandler
    private final Listener<KeyEvent> keyListener = new Listener<>(event ->
            Keybind.getKeybinds().stream().filter(keybind -> keybind.getType() == Keybind.Type.TOGGLE && keybind.getKey() == event.getKey()).forEach(Keybind::onClick));

    /**
     * Handles profiling events
     */
    @EventHandler
    private final Listener<ProfilerEvent> profilerListener = new Listener<>(event -> {
        String section = event.getSection();

        if (section != null && section.equalsIgnoreCase("hand") && !Camera.isCapturing())
            ClientAPI.EVENT_BUS.post(new Render3DEvent());
    });

    /**
     * Handles key states
     */
    @EventHandler
    private final Listener<TickEvent> tickListener = new Listener<>(event -> {
        if (mc.currentScreen != null) return;

        for (int i = 1; i < KEYBOARD_SIZE; i++) {
            final int key = i;
            boolean currentState = Keyboard.isKeyDown(i);
            if (currentState != keyMap[i]) {
                if (keyMap[i] = !keyMap[i])
                    ClientAPI.EVENT_BUS.post(new KeyEvent(i));

                Stream<Keybind> keybinds = Keybind.getKeybinds().stream().filter(keybind -> keybind.getKey() == key);
                if (currentState)
                    keybinds.forEach(Keybind::onPress);
                else
                    keybinds.forEach(Keybind::onRelease);
            }
        }
    });

    /**
     * Handles chat messages
     */
    @EventHandler
    private final Listener<PacketEvent> packetListener = new Listener<>(event -> {
        if (event.getPacket() instanceof CPacketChatMessage) {
            CPacketChatMessage packet = (CPacketChatMessage) event.getPacket();
            ChatEvent chatEvent = new ChatEvent(packet.getMessage(), ChatEvent.Type.SEND);
            ClientAPI.EVENT_BUS.post(chatEvent);
            if (chatEvent.isCancelled())
                event.cancel();
        } else if (event.getPacket() instanceof SPacketChat) {
            SPacketChat packet = (SPacketChat) event.getPacket();
            ClientAPI.EVENT_BUS.post(new ChatEvent(packet.getChatComponent(), ChatEvent.Type.RECEIVE));
        }
    }, new PacketFilter(CPacketChatMessage.class, SPacketChat.class));
}
