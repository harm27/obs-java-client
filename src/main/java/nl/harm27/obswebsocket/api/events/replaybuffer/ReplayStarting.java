package nl.harm27.obswebsocket.api.events.replaybuffer;

import nl.harm27.obswebsocket.api.events.BaseEvent;

/**
 * A request to start the replay buffer has been issued.
 *
 * @see <a href="https://github.com/Palakis/obs-websocket/blob/4.x-current/docs/generated/protocol.md#ReplayStarting">OBS WebSocket Documentation</a>
 * @since v4.2.0
 */
public class ReplayStarting extends BaseEvent {
}