package io.ordini.order.controller.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OrderException extends RuntimeException {
  private HttpStatus status;

  public OrderException(String message) {
    super(message);
  }

  public OrderException(String defaultMessage, HttpStatus status) {
    super(defaultMessage);
    this.status = status;
  }
}
