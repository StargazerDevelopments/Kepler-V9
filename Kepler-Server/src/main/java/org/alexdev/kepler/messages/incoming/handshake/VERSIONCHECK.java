package org.alexdev.kepler.messages.incoming.handshake;

import org.alexdev.kepler.game.player.Player;
import org.alexdev.kepler.messages.types.MessageEvent;
import org.alexdev.kepler.server.netty.streams.NettyRequest;

public class VERSIONCHECK implements MessageEvent {
    @Override
    public void handle(Player player, NettyRequest reader) throws Exception {
        if (player.isLoggedIn()) {
            return;
        }

        int clientVersionId = reader.readInt();
        String clientUrl = reader.readString();
        String externalVarsUrl = reader.readString();

        // TODO: Maybe figure out a way to check for the right client version, or something, idk..
    }
}
