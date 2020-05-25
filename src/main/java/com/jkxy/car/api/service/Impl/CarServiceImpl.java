package com.jkxy.car.api.service.Impl;

import com.jkxy.car.api.dao.CarDao;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("carService")
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findById(int id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findByCarName(String carName) {
        return carDao.findByCarName(carName);
    }

    @Override
    public void deleteById(int id) {
        carDao.deleteById(id);
    }

    @Override
    public void updateById(Car car) {
        carDao.updateById(car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }

    @Override
    public String buyCar(int id) {
        String result = "没有该车型";
        Car searchCar = carDao.findById(id);
        if (searchCar==null){
            return result;
        }
        else{
            String carName = searchCar.getCarName();
            String carType = searchCar.getCarType();
            String price = searchCar.getPrice();
            String carSeries = searchCar.getCarSeries();
            int carNum = searchCar.getCarNum();
            if(carNum>=1){
                carNum -= 1;
                Car putCar =new Car(id, carName, carType, price, carSeries, carNum);
                carDao.updateById(putCar);
                result = "购买成功";
            }else{
                result = "库存不足,购买失败";
            }
        }
        return result;
    }
}
