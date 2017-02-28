/**
 * Created by rubenvarela on 2/28/17.
 */
$(document).ready(function () {
    $('.button').click(function() {

        $('.button').removeClass('active');
        $(this).addClass('active');

        $('input').val($(this).val());


        console.log($('input').val());
    });

    var clicked = false;

    $('.button').last().click(function() {
        if (clicked == false) {
            $(this).css('transform', 'translateY(-9px)');
            $('.button').css('display', 'none');
            $(this).css('display', 'inline-block');
            $(this).text('X');
            $('input').css('display', 'inline-block');
            $('.dollar').css('display', 'inline-block');
            clicked = true
        } else {
            $(this).css('transform', 'none');
            $('input').css('display', 'none');
            $('.button').css('display', 'inline-block');
            $(this).text('Custom');
            $('.button').removeClass('active');
            $('.dollar').css('display', 'none');
            $('.button:nth-of-type(2)').addClass('active');
            clicked = false
        }
    })
});