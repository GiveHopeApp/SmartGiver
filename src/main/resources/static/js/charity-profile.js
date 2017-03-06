/**
 * Created by rubenvarela on 2/28/17.
 */
$(document).ready(function () {

    $('button').each(function () {
        if ($(this).hasClass('active') == true) {
            $('#customAmount').val($(this).val());
        }
        checkInput();
    });

    $('.submit-btn').attr('disabled', true);

    $('#customAmount').keyup(function() {
        checkInput();
    });

    $('.button').click(function() {

        $('.button').removeClass('active');
        $(this).addClass('active');

        $('#customAmount').val($(this).val());

        checkInput();
    });

    var clicked = false;

    $('.button').last().click(function() {
        if (clicked == false) {
            $(this).css('transform', 'translateY(-9px)');
            $('.submit-btn').css('transform', 'translateY(-9px)');
            $('.button').css('display', 'none');
            $(this).css('display', 'inline-block');
            $(this).text('X');
            $('#customAmount').css('display', 'inline-block');
            $('.dollar').css('display', 'inline-block');
            clicked = true
        } else {
            $(this).css('transform', 'none');
            $('.submit-btn').css('transform', 'none');
            $('#customAmount').css('display', 'none');
            $('.button').css('display', 'inline-block');
            $(this).text('Other');
            $('.button').removeClass('active');
            $('.dollar').css('display', 'none');
            $('.button:nth-of-type(2)').addClass('active');
            clicked = false
        }
    });


    function checkInput() {
        if ($('#customAmount').val().length != 0) {
            $('.submit-btn').attr('disabled', false);
        } else {
            $('.submit-btn').attr('disabled', true);
        }
    }

});