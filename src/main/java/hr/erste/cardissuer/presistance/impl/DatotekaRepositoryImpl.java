package hr.erste.cardissuer.presistance.impl;

import hr.erste.cardissuer.models.Datoteka;
import hr.erste.cardissuer.presistance.DatotekaRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatotekaRepositoryImpl implements DatotekaRepository {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    @Override
    public Datoteka getDatoteka(String oib) {
        String sql = "SELECT OIB, FILENAME, CREATED, INACTIVE FROM DATOTEKA WHERE OIB = :oib and INACTIVE IS NULL";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("oib", oib);
        return namedJdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper<>(Datoteka.class));


    }

    @Override
    public boolean createDatoteka(Datoteka datoteka) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("oib", datoteka.getOib())
                .addValue("filename", datoteka.getFilename());

        String sql = "INSERT INTO DATOTEKA (OIB, FILENAME) VALUES (:oib,:filename)";
        return namedJdbcTemplate.update(sql,namedParameters)>0;
    }
}
