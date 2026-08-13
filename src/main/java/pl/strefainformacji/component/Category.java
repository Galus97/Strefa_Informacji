package pl.strefainformacji.component;

import lombok.Getter;

@Getter
public enum Category {
    POLITYKA_KRAJOWA("Polityka Krajowa"),
    POLITYKA_ZAGRANICZNA("Polityka Zagraniczna"),
    SPOLECZENSTWO("Społeczeństwo"),
    GOSPODARKA("Gospodarka i Ekonomia"),
    PRAWO("Prawo i Wymiar Sprawiedliwości"),
    PUBLICYSTYKA("Publicystyka i Opinie"),
    BEZPIECZENSTWO("Bezpieczeństwo i Obronność"),
    EKOLOGIA("Ekologia i Klimat"),
    ZDROWIE("Zdrowie i Medycyna"),
    KULTURA("Kultura i Historia");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }
}
