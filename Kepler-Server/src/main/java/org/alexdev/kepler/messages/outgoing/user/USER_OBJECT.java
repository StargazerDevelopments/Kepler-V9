package org.alexdev.kepler.messages.outgoing.user;

import org.alexdev.kepler.game.player.PlayerDetails;
import org.alexdev.kepler.messages.types.MessageComposer;
import org.alexdev.kepler.server.netty.streams.NettyResponse;

public class USER_OBJECT extends MessageComposer {
    private final PlayerDetails details;

    public USER_OBJECT(PlayerDetails details) {
        this.details = details;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeString(details.getName());
        response.writeString(details.getFigure());
        response.writeString(details.getSex());
        response.writeString(details.getMotto());
        response.writeInt(details.getTickets());
        response.writeString(details.getPoolFigure());
        response.writeInt(details.getFilm());
        response.writeBool(false);
    }

    @Override
    public short getHeader() {
        return 5; // "@E"
    }
}
