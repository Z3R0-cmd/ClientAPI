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

package clientapi.event.defaults.filters;

import clientapi.event.defaults.game.network.PacketEvent;
import net.minecraft.network.Packet;

/**
 * Stricter packet filter. Types are valid if their
 * class is the same as one of the permitted types.
 *
 * @author Brady
 * @since 9/12/2017
 */
public final class StrictPacketFilter<T extends PacketEvent> extends AbstractPacketFilter<T> {

    @SafeVarargs
    public StrictPacketFilter(Class<? extends Packet<?>>... packets) {
        super(packets);
    }

    @Override
    public boolean test(T packetEvent) {
        for (Class<? extends Packet<?>> packet : packets)
            if (packet.equals(packetEvent.getPacket().getClass()))
                return true;

        return false;
    }
}
