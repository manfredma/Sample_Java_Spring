/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package hibernate.type.descriptor.sql;

import org.hibernate.type.descriptor.ValueBinder;
import org.hibernate.type.descriptor.ValueExtractor;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.descriptor.java.JavaTypeDescriptor;
import org.hibernate.type.descriptor.sql.BasicBinder;
import org.hibernate.type.descriptor.sql.BasicExtractor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;

/**
 * Descriptor for {@link Types#TIMESTAMP TIMESTAMP} handling.
 *
 * @author Steve Ebersole
 */
public class ZonedDateTimeTypeDescriptor implements SqlTypeDescriptor {
    public static final ZonedDateTimeTypeDescriptor INSTANCE = new ZonedDateTimeTypeDescriptor();

    public ZonedDateTimeTypeDescriptor() {
    }

    @Override
    public int getSqlType() {
        return Types.TIMESTAMP_WITH_TIMEZONE;
    }

    @Override
    public boolean canBeRemapped() {
        return true;
    }

    @Override
    public <X> ValueBinder<X> getBinder(final JavaTypeDescriptor<X> javaTypeDescriptor) {
        return new BasicBinder<X>(javaTypeDescriptor, this) {
            @Override
            protected void doBind(PreparedStatement st, X value, int index, WrapperOptions options) throws SQLException {
                final Timestamp timestamp = javaTypeDescriptor.unwrap(value, Timestamp.class, options);
                st.setObject(index, ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.of("+1")));
//                if (st instanceof DruidPooledPreparedStatement) {
//                    PreparedStatement raw = ((DruidPooledPreparedStatement)st).getRawPreparedStatement();
//                    raw.setObject(index, ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.of("+1")), JDBCType.TIMESTAMP_WITH_TIMEZONE);
//                } else {
//                    st.setObject(index, ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.of("+1")), JDBCType.TIMESTAMP_WITH_TIMEZONE);
//                }
            }

            @Override
            protected void doBind(CallableStatement st, X value, String name, WrapperOptions options) throws SQLException {
                final Timestamp timestamp = javaTypeDescriptor.unwrap(value, Timestamp.class, options);
                st.setObject(name, ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.of("+1")));
//                if (st instanceof DruidPooledPreparedStatement) {
//                    CallableStatement raw = (CallableStatement)((DruidPooledPreparedStatement)st).getRawPreparedStatement();
//                    raw.setObject(name, ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.of("+1")), JDBCType.TIMESTAMP_WITH_TIMEZONE);
//                } else {
//                    st.setObject(name, ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.of("+1")), JDBCType.TIMESTAMP_WITH_TIMEZONE);
//                }
            }
        };
    }

    @Override
    public <X> ValueExtractor<X> getExtractor(final JavaTypeDescriptor<X> javaTypeDescriptor) {
        return new BasicExtractor<X>(javaTypeDescriptor, this) {
            @Override
            protected X doExtract(ResultSet rs, String name, WrapperOptions options) throws SQLException {
                return options.getJdbcTimeZone() != null ? javaTypeDescriptor.wrap(rs.getTimestamp(name, Calendar.getInstance(options.getJdbcTimeZone())), options) : javaTypeDescriptor.wrap(rs.getTimestamp(name), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, int index, WrapperOptions options) throws SQLException {
                return options.getJdbcTimeZone() != null ? javaTypeDescriptor.wrap(statement.getTimestamp(index, Calendar.getInstance(options.getJdbcTimeZone())), options) : javaTypeDescriptor.wrap(statement.getTimestamp(index), options);
            }

            @Override
            protected X doExtract(CallableStatement statement, String name, WrapperOptions options) throws SQLException {
                return options.getJdbcTimeZone() != null ? javaTypeDescriptor.wrap(statement.getTimestamp(name, Calendar.getInstance(options.getJdbcTimeZone())), options) : javaTypeDescriptor.wrap(statement.getTimestamp(name), options);
            }
        };
    }
}
