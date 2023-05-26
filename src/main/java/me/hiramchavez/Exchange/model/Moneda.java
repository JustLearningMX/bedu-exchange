package me.hiramchavez.Exchange.model;

public enum Moneda {
    MXN("1", "0.056","0.052"),
    USD("17.86", "1", "0.93"),
    EUR("19.23", "1.07","1");

    private String mxn;
    private String usd;
    private String eur;

    private Moneda(String mxn, String usd, String eur) {
        this.mxn = mxn;
        this.usd = usd;
        this.eur = eur;
    }

    public String getMxn() {
        return mxn;
    }

    public String getUsd() {
        return usd;
    }

    public String getEur() {
        return eur;
    }

    public String getValor(String moneda1, String moneda2) {
        return switch (moneda1) {
            case "MXN" -> switch (moneda2) {
                case "USD" -> Moneda.MXN.getUsd();
                case "EUR" -> Moneda.MXN.getEur();
                default -> "1";
            };
            case "USD" -> switch (moneda2) {
                case "MXN" -> Moneda.USD.getMxn();
                case "EUR" -> Moneda.USD.getEur();
                default -> "1";
            };
            case "EUR" -> switch (moneda2) {
                case "MXN" -> Moneda.EUR.getMxn();
                case "USD" -> Moneda.EUR.getUsd();
                default -> "1";
            };
            default -> "1";
        };
    }
}
