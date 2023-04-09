package org.alexdev.kepler.messages.outgoing.handshake;

import org.alexdev.kepler.game.player.PlayerDetails;
import org.alexdev.kepler.messages.types.MessageComposer;
import org.alexdev.kepler.server.netty.streams.NettyResponse;
import org.alexdev.kepler.util.config.GameConfiguration;

import java.util.HashMap;
import java.util.Map;

public class SESSION_PARAMETERS extends MessageComposer {
    public enum SessionParamType {
        // conf_coppa is enabled when value higher than 0,
        // conf_strong_coppa_required is enabled when value is higher than 1
        REGISTER_COPPA(0),

        // conf_voucher. Determines if vouchers are enabled in the client (in-game)
        VOUCHER_ENABLED(1),

        // conf_parent_email_request. I think this is to switch parent email on/off
        REGISTER_REQUIRE_PARENT_EMAIL(2),

        // conf_parent_email_request_reregistration. ???
        REGISTER_SEND_PARENT_EMAIL(3),

        // conf_allow_direct_mail. ???
        ALLOW_DIRECT_MAIL(4),

        // Configures date formatting. Value is date string.
        DATE_FORMAT(5);

        private final int paramID;

        SessionParamType(int paramID) {
            this.paramID = paramID;
        }

        public int getParamID() {
            return this.paramID;
        }
    }

    @Override
    public void compose(NettyResponse response) {
        Map<SessionParamType, Object> parameters = new HashMap<>();

        parameters.put(SessionParamType.REGISTER_COPPA, 0); // conf_coppa
        parameters.put(SessionParamType.VOUCHER_ENABLED, GameConfiguration.getInstance().getBoolean("vouchers.enabled") ? 1 : 0); // conf_voucher. Determines if vouchers are enabled in the client (in-game)
        parameters.put(SessionParamType.REGISTER_REQUIRE_PARENT_EMAIL, 0); // conf_parent_email_request. I think this is to switch parent email on/off
        parameters.put(SessionParamType.REGISTER_SEND_PARENT_EMAIL, 0); // conf_parent_email_request_reregistration. ???
        parameters.put(SessionParamType.ALLOW_DIRECT_MAIL, 0); // conf_allow_direct_mail. ???
        parameters.put(SessionParamType.DATE_FORMAT, "dd-MM-yyyy"); // Configures date formatting. Value is date string.

        response.writeInt(parameters.size());

        for (Map.Entry<SessionParamType, Object> entry : parameters.entrySet()) {
            Object value = entry.getValue();

            response.writeInt(entry.getKey().getParamID());

            if (value instanceof Integer) {
                response.writeInt((int)value);
            } else {
                response.writeString(value);
            }
        }
    }

    @Override
    public short getHeader() {
        return 257;
    }
}