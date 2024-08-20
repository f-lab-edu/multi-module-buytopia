package com.zeroskill.common.service;

import com.zeroskill.common.dto.ProductQuantityDTO;
import com.zeroskill.common.entity.Cart;
import com.zeroskill.common.entity.CartItem;
import com.zeroskill.common.entity.Member;
import com.zeroskill.common.entity.Product;
import com.zeroskill.common.exception.BuytopiaException;
import com.zeroskill.common.repository.CartRepository;
import com.zeroskill.common.repository.MemberRepository;
import com.zeroskill.common.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zeroskill.common.exception.ErrorType.DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CartService {

    private static final Logger logger = LogManager.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void addProductToCart(Long memberId, List<ProductQuantityDTO> products) {
        Cart cart = cartRepository.findByMemberId(memberId)
                .orElseGet(() -> createNewCart(memberId));

        products.stream()
                .map( productQuantityDTO -> {
                    Product product = productRepository.findById(productQuantityDTO.productId())
                            .orElseThrow(() -> new BuytopiaException(DATA_NOT_FOUND, logger::error));
                    return new CartItem(cart, product, productQuantityDTO.quantity());
                })
                .forEach(cart::addCartItem);
    }

    private Cart createNewCart(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BuytopiaException(DATA_NOT_FOUND, logger::error));
        Cart cart = new Cart(member);
        return cartRepository.save(cart);
    }
}
