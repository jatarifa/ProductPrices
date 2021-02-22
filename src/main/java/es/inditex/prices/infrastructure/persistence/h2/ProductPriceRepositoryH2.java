package es.inditex.prices.infrastructure.persistence.h2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import es.inditex.prices.domain.model.ProductPrice;
import es.inditex.prices.domain.model.ProductPriceFactory;
import es.inditex.prices.domain.model.ProductPriceRepository;

@Repository
public class ProductPriceRepositoryH2 implements ProductPriceRepository {
	private static String FIND_ALL = "select * from prices where" +
 								     "   product_id = ?" +
 								     "   and brand_id = ?" +
 								     "   and start_date <= ?" +
									 "   and end_date >= ?";

	private final JdbcTemplate jdbcTemplate;

	public ProductPriceRepositoryH2(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ProductPrice> findAllProductPricesForDate(Long brand, Long product, LocalDateTime date) {
		return jdbcTemplate.query(FIND_ALL, new Object[] { product, brand, date, date }, (rs, rowNum) -> {
			Long price_list = rs.getLong("price_list");
			Long brand_id = rs.getLong("brand_id");
			Long product_id = rs.getLong("product_id");
			Long priority = rs.getLong("priority");
			BigDecimal price = rs.getBigDecimal("price");
			String currency = rs.getString("curr");
			LocalDateTime start_date = rs.getTimestamp("start_date").toLocalDateTime();
			LocalDateTime end_date = rs.getTimestamp("end_date").toLocalDateTime();

			return ProductPriceFactory.createProductPrice(price_list, brand_id, product_id, priority, 
														  price, currency, start_date, end_date);
		});
	}
}