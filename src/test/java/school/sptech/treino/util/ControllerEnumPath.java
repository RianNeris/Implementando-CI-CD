package school.sptech.treino.util;

public enum ControllerEnumPath {

    URL_BASE("http://localhost:8080/personagens"),
    POR_ID("%s/{id}".formatted(URL_BASE.path)),
    POR_CODINOME("%s/codinome".formatted(URL_BASE.path)),
    POR_DATA_NASCIMENTO("%s/nascidos-apos".formatted(URL_BASE.path)),
    TOP3("%s/top-3".formatted(URL_BASE.path)),
    MENOR_PODER("%s/menor-poder".formatted(URL_BASE.path));

    ControllerEnumPath(String path) {
        this.path = path;
    }

    public final String path;
}
