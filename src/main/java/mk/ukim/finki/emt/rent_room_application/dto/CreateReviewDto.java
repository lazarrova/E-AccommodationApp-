package mk.ukim.finki.emt.rent_room_application.dto;

import mk.ukim.finki.emt.rent_room_application.model.domain.Review;

public record CreateReviewDto(
        String comment,
        Integer rate
) {

    public Review toReview(){
        return new Review(comment,rate);
    }

    @Override
    public String comment() {
        return comment;
    }

    @Override
    public Integer rate() {
        return rate;
    }
}