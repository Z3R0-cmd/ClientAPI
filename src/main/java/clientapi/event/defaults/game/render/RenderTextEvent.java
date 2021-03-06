/*
 * Copyright 2018 ImpactDevelopment
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

package clientapi.event.defaults.game.render;

/**
 * Called in FontRenderer when text is rendered
 * and string width is checked
 *
 * @author Brady
 * @since 3/30/2017
 */
public final class RenderTextEvent {

    /**
     * The text being rendered
     */
    private String text;

    public RenderTextEvent(String text) {
        this.text = text;
    }

    /**
     * @return The text being rendered
     */
    public final String getText() {
        return this.text;
    }

    /**
     * Sets the text being rendered
     *
     * @param text New text
     * @return This event
     */
    public final RenderTextEvent setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String toString() {
        return "TextEvent{" +
                "text='" + text + '\'' +
                '}';
    }
}
