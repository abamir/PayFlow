## Custom Query Approaches in Spring Data JPA

Spring Data JPA provides multiple ways to write database queries. The three most common approaches are:

### 1. Derived Method Names

Spring automatically generates queries based on repository method names.

#### Example

```java
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUpiId(String upiId);

    List<User> findByNameContaining(String name);
}
```

#### Generated Query

```sql
SELECT * FROM users WHERE upi_id = ?
```

#### Advantages

- No SQL or JPQL required.
- Easy to read and maintain.
- Type-safe and less error-prone.
- Database-independent.

#### Disadvantages

- Method names can become very long and difficult to read for complex conditions.
- Limited flexibility for advanced queries.

---

### 2. @Query with JPQL

JPQL (Java Persistence Query Language) works with entity classes and their fields rather than database tables and columns.

#### Example

```java
@Query("SELECT u FROM User u WHERE u.upiId = :upiId")
Optional<User> findUserByUpiId(@Param("upiId") String upiId);
```

#### Advantages

- More flexible than derived methods.
- Uses entity names and Java field names.
- Database-independent.
- Easier to refactor because it relies on entities.

#### Disadvantages

- Requires manually writing query strings.
- Slightly more complex than derived methods.

---

### 3. Native SQL Queries

Native queries allow writing raw SQL directly.

#### Example

```java
@Query(
    value = "SELECT * FROM users WHERE upi_id = :upiId",
    nativeQuery = true
)
Optional<User> findUserNative(@Param("upiId") String upiId);
```

#### Advantages

- Full SQL power available.
- Useful for complex joins, database-specific functions, and performance tuning.
- Can leverage vendor-specific features.

#### Disadvantages

- Database-dependent.
- Harder to maintain.
- Not portable across databases.
- Refactoring database columns can easily break queries.
- Bypasses many JPA abstractions and benefits.

---

## Comparison

| Feature | Derived Method | JPQL (@Query) | Native SQL |
|----------|---------------|---------------|------------|
| Ease of Use | High | Medium | Low |
| Readability | High | Medium | Medium |
| Flexibility | Low | High | Very High |
| Database Independent | Yes | Yes | No |
| Uses Entity Fields | Yes | Yes | No |
| Uses Table Columns | No | No | Yes |
| Maintenance Effort | Low | Medium | High |
| Recommended Usage | First Choice | Second Choice | Last Resort |

---

## Why Native Queries Are the Least Preferred

Native SQL queries should generally be used only when derived methods or JPQL cannot satisfy the requirement.

Reasons:

1. **Database Vendor Lock-In**
    - SQL syntax may differ between MySQL, PostgreSQL, Oracle, and SQL Server.
    - Migrating databases becomes more difficult.

2. **Reduced Maintainability**
    - Queries depend on table and column names.
    - Schema changes can break queries silently.

3. **Loss of JPA Abstraction**
    - JPA is designed to work with entities and object-oriented models.
    - Native SQL bypasses many of these advantages.

4. **Refactoring Risk**
    - Renaming a Java entity field updates JPQL more naturally.
    - Native SQL must be manually updated.

5. **Lower Portability**
    - A query written for one database may fail on another without modification.

### Recommended Order

```text
1. Derived Method Names   ← Preferred
2. JPQL (@Query)          ← Use when query becomes complex
3. Native SQL             ← Use only when absolutely necessary
```

### Rule of Thumb

- Use **Derived Methods** for simple lookups.
- Use **JPQL** for custom business queries.
- Use **Native SQL** only for database-specific optimizations or features that JPQL cannot support.