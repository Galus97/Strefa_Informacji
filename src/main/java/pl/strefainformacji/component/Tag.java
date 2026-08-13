package pl.strefainformacji.component;

import lombok.Getter;

@Getter
public enum Tag {
    WYBORY("Wybory"),
    SEJM_SENAT("Sejm i Senat"),
    UNIA_EUROPEJSKA("Unia Europejska"),
    NATO("NATO"),
    INFLACJA("Inflacja"),
    PODATKI("Podatki"),
    MIESZKALNICTWO("Mieszkalnictwo"),
    PROTESTY("Protesty i Strajki"),
    SAMORZAD("Samorząd Terytorialny"),
    GEOPOLITYKA("Geopolityka"),
    PRAWA_CZLOWIEKA("Prawa Człowieka"),
    SADOWNICTWO("Sądownictwo"),
    ENERGETYKA("Transformacja Energetyczna"),
    MEDIA("Media i Informacja"),
    MIGRACJE("Migracje i Granice"),
    EDUKACJA("Edukacja i Szkolnictwo");

    private final String displayName;

    Tag(String displayName) {
        this.displayName = displayName;
    }
}
