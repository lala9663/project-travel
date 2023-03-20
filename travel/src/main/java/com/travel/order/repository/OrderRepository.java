package com.travel.order.repository;

import com.travel.member.entity.Member;
import com.travel.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderIdAndMember(Long orderId, Member member);
}
