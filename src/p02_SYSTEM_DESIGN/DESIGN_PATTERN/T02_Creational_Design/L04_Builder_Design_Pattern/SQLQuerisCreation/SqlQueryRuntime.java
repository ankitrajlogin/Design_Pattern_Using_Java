package p02_SYSTEM_DESIGN.DESIGN_PATTERN.T02_Creational_Design.L04_Builder_Design_Pattern.SQLQuerisCreation;

/*
Problem Statement

We need to build SQL queries dynamically in code.

For example:
    SELECT name, age
    FROM users
    WHERE age > 18
    ORDER BY name;
 */

class SQLQuery{
    private final String query ;

    private SQLQuery(Builder builder){
        this.query = builder.buildQuery() ;
    }

    public String getQuery(){
        return query ;
    }


    // Static inner builder
    public static class Builder{
        private String table;
        private String columns = "*";
        private String condition;
        private String orderBy;
        private Integer limit;

        public Builder select(String columns){
            this.columns = columns ;
             return this ;
        }

        public Builder from(String table){
            this.table = table ;
            return this ;
        }

        public Builder where(String condition){
            this.condition = condition ;
            return this ;
        }

        public Builder orderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        private String buildQuery(){
            if (table == null) {
                throw new IllegalStateException("Table name is required!");
            }

            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ").append(columns).append(" FROM ").append(table);

            if (condition != null) {
                sb.append(" WHERE ").append(condition);
            }

            if (orderBy != null) {
                sb.append(" ORDER BY ").append(orderBy);
            }

            if (limit != null) {
                sb.append(" LIMIT ").append(limit);
            }

            return sb.toString() ;
        }

        public SQLQuery build(){
            return new SQLQuery(this) ;
        }
    }
}

// client code
public class SqlQueryRuntime {
    public static void main(String[] args){
        SQLQuery query1 = new SQLQuery.Builder()
                .select("name, age")
                .from("users")
                .where("age > 18")
                .orderBy("name")
                .build();

        System.out.println(query1.getQuery());


        // taking input from the json which is coming from the frontent .

    }
}
