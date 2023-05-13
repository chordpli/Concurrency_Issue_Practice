package com.example.stock.facade;

import org.springframework.stereotype.Component;

import com.example.stock.repository.RedisLockReposiotry;
import com.example.stock.repository.StockRepository;
import com.example.stock.service.StockService;

@Component
public class LettuceLockStockFacade {

	private RedisLockReposiotry redisLockReposiotry;
	private StockService stockService;

	public LettuceLockStockFacade(RedisLockReposiotry redisLockReposiotry, StockService stockService) {
		this.redisLockReposiotry = redisLockReposiotry;
		this.stockService = stockService;
	}

	public void decrease(Long key, Long quantity) throws InterruptedException {
		while (!redisLockReposiotry.lock(key)) {
			Thread.sleep(100);
		}

		try{
			stockService.decrease(key, quantity);
		} finally {
			redisLockReposiotry.unlock(key);
		}
	}
}
