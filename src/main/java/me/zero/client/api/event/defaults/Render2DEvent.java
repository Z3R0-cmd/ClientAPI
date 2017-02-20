package me.zero.client.api.event.defaults;

/**
 * Called after GuiIngame#renderGameOverlay(float) is called.
 *
 * @since 1.0
 *
 * Created by Brady on 2/6/2017.
 */
public final class Render2DEvent {

    /**
     * The partial ticks
     */
    private final float partialTicks;

    public Render2DEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    /**
     * @since 1.0
     *
     * @return The partial ticks
     */
    public float getPartialTicks() {
        return this.partialTicks;
    }
}
