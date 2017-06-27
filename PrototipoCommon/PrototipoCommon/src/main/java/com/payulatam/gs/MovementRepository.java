package com.payulatam.gs;

import java.math.BigDecimal;
import java.util.Date;

import org.openspaces.core.GigaSpace;

import com.payulatam.model.Movement;

/**
 * Implementation of repository by GigaSpace for Movement
 * @author John Quiroga
 *
 * @param <BaseEntity>
 */
public class MovementRepository<J extends Movement> extends AbstractRepository<Movement> implements IMovementRepository {

	public MovementRepository(GigaSpace gigaSpace) {
		super.gigaSpace = gigaSpace;
	}

	@Override
	public Movement[] serach(String customerId, String typeMovement, Date date, BigDecimal balance) {
		String queryString = generateStringQuery(customerId, typeMovement, date, balance);
		Movement[] result = findByCriteria(queryString);
		return result;
	}
	
	public String generateStringQuery(String customerId, String typeMovement, Date date, BigDecimal balance) {
		StringBuilder query = new StringBuilder();
		if (customerId != null && !"*".equals(customerId) && !customerId.isEmpty()) {
			query.append(String.format("accountId = '%s'", customerId));
		}
		
		if (typeMovement != null && !"*".equals(typeMovement) && !typeMovement.isEmpty()) {
			if (!query.toString().isEmpty()) {
				query.append(" and ");
			}
			query.append(String.format(" type = '%s' ", typeMovement));
		}
		if (date != null) {
			if (!query.toString().isEmpty()) {
				query.append(" and ");
			}
			query.append(String.format(" date = '%s' ", date));
		}
		
		if (balance != null) {
			if (!query.toString().isEmpty()) {
				query.append(" and ");
			}
			query.append(String.format(" value = %s ", balance));
		}
		query.append(" ORDER BY com.payulatam.model.Movement.date");
		
		return query.toString();
	}
	
}
