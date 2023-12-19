package com.pro.privacidade.core.constants;

import java.util.ArrayList;
import java.util.List;

public class ReasonData {
    private final List<String> reasonDataList;

    public ReasonData() {
        reasonDataList = new ArrayList<>();
        reasonDataList.add("Consentimento - Mendiante consetimento do titular");
        reasonDataList.add("Regulatório - Para cumprimento de obrigação legal ou regulatória pelo controlador");
        reasonDataList.add("Governo - Pela administração pública, para tratamento de dados necessários a política pública");
        reasonDataList.add("Pesquisa - Para realização de estudos por órgão de pesquisa, sendo garantida a anonimização dos dados");
        reasonDataList.add("Contratos - Quando necessário para a execução de contrato");
        reasonDataList.add("Judicial - Exercício regular de direitos, inclusive em contrato e em processo judicial, administrativo e arbitral");
        reasonDataList.add("Vida - Para a proteção da vida ou incolumidade física do títular ou terceiros");
        reasonDataList.add("Saúde - Para a tutela da pádida, com procedimento realizado por profissionais da área da pessoa ou por entidades sanitárias");
        reasonDataList.add("Legítimo interesse - Interesses legítimos do controlador ou de terceiro");
        reasonDataList.add("Crédito - Para proteção do crédito");
        reasonDataList.add("Dados sensíveis - Para garantia da prevenção à fraude e à segurança do títular");
    }

    public List<String> getReasonDataList() {
        return reasonDataList;
    }
}
