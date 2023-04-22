package hr.erste.cardissuer.presistance.impl;

import hr.erste.cardissuer.models.Osoba;
import hr.erste.cardissuer.presistance.OsobaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Component
public class OsobaRepositoryImpl implements OsobaRepository {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    @Override
    public Osoba getOsoba(String oib) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("oib", oib);
        String sql = "SELECT IME, PREZIME, OIB, CREATED, INACTIVE FROM OSOBA WHERE INACTIVE IS NULL AND OIB = :oib";
        return namedJdbcTemplate.queryForObject(sql,namedParameters, new BeanPropertyRowMapper<>(Osoba.class));

    }

    @Override
    public boolean createOsoba(Osoba osoba) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("oib", osoba.getOib())
                .addValue("ime",osoba.getIme())
                .addValue("prezime", osoba.getPrezime());
        String sql = "INSERT INTO OSOBA ( OIB, IME, PREZIME) VALUES(:oib,:ime,:prezime)";
        return namedJdbcTemplate.update(sql,namedParameters)>0;

    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean deleteOsoba(String oib) {

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("oib", oib);
        String sql = "UPDATE DATOTEKA SET INACTIVE = CURRENT_TIMESTAMP WHERE OIB = :oib AND INACTIVE IS NULL";
        namedJdbcTemplate.update(sql,namedParameters);
        sql = "UPDATE OSOBA SET INACTIVE = CURRENT_TIMESTAMP WHERE OIB = :oib AND INACTIVE IS NULL";
        return namedJdbcTemplate.update(sql,namedParameters)>0;



    }

    @Override
    public List<Osoba> getOsobe() {
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        String sql = "SELECT IME, PREZIME, OIB, CREATED, INACTIVE FROM OSOBA ORDER BY IME, PREZIME, CREATED DESC ";
        return namedJdbcTemplate.query(sql,namedParameters,new BeanPropertyRowMapper<>(Osoba.class));
    }
}
