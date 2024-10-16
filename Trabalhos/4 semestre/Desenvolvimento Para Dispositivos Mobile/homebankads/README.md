Projeto HomeBank ADS
====================================================================================================
Projeto já usado em aulas passadas com registro de dados em arquivos. Agora está usando SQLite local
na disciplina de programação para dispositivos móveis turma ADS 19.


TRIGGER ADICIONADA JUNTO COM A TABELA MOVIMENTO

    String cria_t_deposito =" CREATE TRIGGER IF NOT EXISTS deposito\n" +
            "         AFTER INSERT\n" +
            "            ON movimento\n" +
            "      FOR EACH ROW\n" +
            "BEGIN\n" +
            "    UPDATE usuario\n" +
            "       SET saldo = saldo + NEW.valor\n" +
            "     WHERE id = new.idusr;\n" +
            "END;";
