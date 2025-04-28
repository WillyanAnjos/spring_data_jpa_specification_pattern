# Consultas com Specification Pattern

Este projeto demonstra o uso de **Specification** no Spring Data JPA para construir consultas dinâmicas, reutilizáveis e complexas de forma programática, seguindo o padrão **Specification Pattern**.

## Sobre o Specification

O **Specification** permite criar filtros de consulta flexíveis e reutilizáveis, combinando múltiplas condições de forma simples e elegante. Ele facilita a construção de cláusulas como `WHERE`, `AND`, `OR` e `NOT` dinamicamente durante a execução.

## Construindo Consultas Reutilizáveis

As **Specifications** são criadas de forma modular e podem ser combinadas para construir consultas mais complexas.

### Funcionamento

- **Interface Specification**: Define o método `toPredicate`, que cria uma cláusula de consulta.
  
```java
@FunctionalInterface
public interface Specification<T> {
    Predicate toPredicate(Root<T> root, 
                          CriteriaQuery<?> query, 
                          CriteriaBuilder builder);
}

public class ProductSpecification {
    public static Specification<Product> hasCategory(String category) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("category"), category);
    }
 
    public static Specification<Product> hasPriceGreaterThan(Double price) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThan(root.get("price"), price);
    }
}

@Repository
public interface ProductRepository extends 
                JpaRepository<Product, Long>, 
                JpaSpecificationExecutor<Product> {
}

Specification<Product> spec = Specification
        .where(ProductSpecification.hasCategory("Electronics"))
        .and(ProductSpecification.hasPriceGreaterThan(100.0));

List<Product> products = productRepository.findAll(spec);
```

Documentação Swagger
A documentação da API está disponível no link abaixo:
http://localhost:8080/swagger-ui/index.html