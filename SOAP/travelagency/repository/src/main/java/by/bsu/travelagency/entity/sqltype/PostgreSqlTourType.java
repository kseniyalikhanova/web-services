package by.bsu.travelagency.entity.sqltype;

import by.bsu.travelagency.entity.enumeration.Feature;
import by.bsu.travelagency.entity.enumeration.TourType;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PostgreSqlTourType implements UserType {

    private static final int SQL_TYPE = Types.VARCHAR;

    @Override
    public int[] sqlTypes() {
        return new int[]{SQL_TYPE};
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<List<Feature>> returnedClass() {
        return (Class<List<Feature>>) Collections.<Feature>emptyList().getClass();
    }

    @Override
    public boolean equals(final Object x, final Object y) {
        return Objects.equals(x, y);
    }

    @Override
    public int hashCode(Object o) {
        return o == null ? 0 : o.hashCode();
    }

    @Override
    public Object nullSafeGet(
            ResultSet resultSet, String[] strings,
            SharedSessionContractImplementor sharedSessionContractImplementor,
            Object o) throws SQLException {
        if (strings != null) {
            return TourType.valueOf(resultSet.getString(strings[0]).replaceAll(" ", "_"));
        } else {
            return new ArrayList<Feature>();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void nullSafeSet(
            PreparedStatement statement, Object object, int i,
            SharedSessionContractImplementor sharedSessionContractImplementor
    ) throws SQLException {
        if (object != null) {
            TourType tourType = (TourType) object;
            statement.setObject(i, tourType, Types.OTHER);
        } else {
            statement.setNull(i, SQL_TYPE);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public Object deepCopy(final Object o) {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) {
        return (Serializable) o;
    }

    @Override
    public Object assemble(final Serializable cached, final Object owner) {
        return cached;
    }

    @Override
    public Object replace(final Object original, final Object target, final Object owner) {
        return original;
    }
}
