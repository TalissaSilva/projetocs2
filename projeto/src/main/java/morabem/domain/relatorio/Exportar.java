package morabem.domain.relatorio;

public class Exportar {

    public static enum Componente {
        CABECALHO,
        DADOS,
        RODAPE
    }

    public static enum Formato {
        JSON(false),
        TXT(true),
        CSV(false);

        private boolean forDownload;

        public boolean forDownload() {
            return forDownload;
        }

        Formato(boolean forDownload) {
            this.forDownload = forDownload;
        }
    }
}
