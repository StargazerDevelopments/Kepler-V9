package org.alexdev.kepler.messages.outgoing.purse;

import org.alexdev.kepler.messages.types.MessageComposer;
import org.alexdev.kepler.server.netty.streams.NettyResponse;

public class VOUCHER_REDEEM_ERROR extends MessageComposer {
    public enum RedeemError {
        TECHNICAL_ERROR(0),
        INVALID(1),
        PRODUCT_DELIVERY_FAILED(2),
        WEB_ONLY(3);

        private int errorCode;

        RedeemError(int errorCode) {
            this.errorCode = errorCode;
        }

        public int getErrorCode() {
            return this.errorCode;
        }
    }

    private final RedeemError error;

    public VOUCHER_REDEEM_ERROR(RedeemError error) {
        this.error = error;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeInt(this.error.getErrorCode());
    }

    @Override
    public short getHeader() {
        return 213; // "CU"
    }
}