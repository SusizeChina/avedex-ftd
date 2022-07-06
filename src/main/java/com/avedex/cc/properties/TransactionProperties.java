package com.avedex.cc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("transaction")
public class TransactionProperties {

    private String xAuthToken = "6b5466a0ae63578a5cfc96c3f5ac9d0f1656734972472208557";

    private String ftc = "0x633cb54a3a0b5a0a59df3d1fc8bc38171d587d96-oec";

    private String ftd = "TRi5H2adh2h5jAsU4GES6HpQQPvn1G2f68-tron";

    private String[] to;
}
