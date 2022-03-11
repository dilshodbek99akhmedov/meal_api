package team.concurrency.mealproject.service.order;

import org.springframework.stereotype.Service;
import team.concurrency.mealproject.dto.TaomlarSoni;
import team.concurrency.mealproject.dto.order.OrderCreateDto;
import team.concurrency.mealproject.dto.order.OrderDto;
import team.concurrency.mealproject.dto.order.OrderUpdateDto;
import team.concurrency.mealproject.entity.Order;
import team.concurrency.mealproject.mapper.order.OrderMapper;
import team.concurrency.mealproject.repository.order.OrderRepository;
import team.concurrency.mealproject.service.base.AbstractService;
import team.concurrency.mealproject.validator.order.OrderValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImp extends AbstractService<OrderRepository, OrderMapper, OrderValidator> implements OrderService {

    protected OrderServiceImp(OrderMapper mapper, OrderValidator validator, OrderRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public String create(OrderCreateDto createDto) {
        ordered();
        Optional<Order> orderMeal = repository.thisUserOrderMeal(createDto.getUserId());
        if (orderMeal.isPresent())
            throw new RuntimeException("No created Order");
        Order order = mapper.fromCreateDto(createDto);
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(createDto.getDate(), dateTimeFormat);
        order.setDate(localDateTime);
        order.setDeleted(false);
        order.setTaken(false);
        System.out.println(order);
        order.setId(UUID.randomUUID().toString());
        return repository.save(order).getId();
    }

    @Override
    public void delete(String id) {
        ordered();
        Order order = repository.thisUserOrderNoDeleted(id);
        orderIsNull(order);
        order.setDeleted(true);
        repository.save(order);
    }

    @Override
    public Boolean update(OrderUpdateDto updateDto) {
        Order order = repository.thisUserOrderNoDeleted(updateDto.getId());
        orderIsNull(order);
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(updateDto.getDate(), dateTimeFormat);
        order.setDate(localDateTime);
        order.setTaken(updateDto.isTaken());
        repository.save(order);
        return true;
    }

    @Override
    public OrderDto get(String id) {
        return null;
    }

    @Override
    public List<OrderDto> getAll(String id) {
        return null;
    }

    @Override
    public List<OrderDto> getAll() {
        return null;
    }

    public List<TaomlarSoni> ordersNumber() {
        return repository.orderNumbers().stream().map(this::process).toList();
    }

    public void ordered() {
        if (LocalDate.now().getDayOfWeek().getValue() == 7 || LocalTime.now().getHour() > 10) {
            throw new RuntimeException("The order cannot be changed now");
        }
    }

    public void orderIsNull(Order order) {
        if (Objects.isNull(order))
            throw new RuntimeException("No deleted Order");
    }

    private TaomlarSoni process(Object[] objects) {
        return new TaomlarSoni(String.valueOf(objects[0]), (Long) objects[1]);
    }
}
