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
        response.writeValue("name", details.getName());
        response.writeValue("figure", details.getFigure());
        response.writeValue("sex", details.getSex());
        response.writeValue("customData", details.getMotto());
        response.writeValue("ph_tickets", details.getTickets());
        response.writeValue("photo_film", details.getFilm());
        response.writeValue("ph_figure", details.getPoolFigure());
        response.writeValue("directMail", 0);
    }

    @Override
    public short getHeader() {
        return 5; // "@E"
    }
}
