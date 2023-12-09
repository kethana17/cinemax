// BookingRepository.java
package com.hexaware.cinemax.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hexaware.cinemax.entities.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT b.seatNumbers FROM Booking b WHERE b.show.id = :showId")
    List<String> findSeatNumbersByShowId(@Param("showId") int showId);

    @Query("SELECT b.seatNumbers FROM Booking b WHERE b.user.id = :userId")

	List<String> findSeatNumbersByUserId(int userId);
}
