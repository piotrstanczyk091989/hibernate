package pl.javaps.entity;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "order-row-customer-product",
                attributeNodes = {
                        @NamedAttributeNode(value = "orderRows", subgraph = "orderRows"),
                        @NamedAttributeNode("customer")
                }, subgraphs = @NamedSubgraph(
                name = "orderRows",
                attributeNodes = @NamedAttributeNode("product"))
        ),
        @NamedEntityGraph(
                name = "order-row",
                attributeNodes = @NamedAttributeNode("orderRows")
        )
})
@Entity
@Table(name = "\"order\"")
@Cacheable
@Cache(region = "order", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime created;
    private BigDecimal total;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
    @BatchSize(size = 10)
    @JoinColumn(name = "order_id")
    private Set<OrderRow> orderRows;

    @OneToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private String uuid = UUID.randomUUID().toString();

    @Version
    private Long version;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(uuid, order.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<OrderRow> getOrderRows() {
        return orderRows;
    }

    public void setOrderRows(Set<OrderRow> orderRows) {
        this.orderRows = orderRows;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", created=" + created +
                ", total=" + total +
                '}';
    }
}
