$(document).ready(function() {


    $('.form').find('input, textarea').on('keyup blur focus', function (e) {

        var label = $(this).siblings('label').first();

        if (e.type === 'keyup') {
            if ($(this).val() === '') {
                label.removeClass('active highlight active-middle');
            } else if (label.hasClass('middle')) {
                label.addClass('active-middle')
            } else {
                label.addClass('active highlight');
            }
        } else if (e.type === 'blur') {
            if($(this).val() === '' ) {
                label.removeClass('active highlight active-middle');
            } else {
                label.removeClass('highlight');
            }
        } else if (e.type === 'focus') {
            if( $(this).val() === '' ) {
                label.removeClass('highlight active-middle');
            } else if($(this).val() !== '' ) {
                label.addClass('highlight');
            }
        }

    });

    $('.tab a').on('click', function (e) {

      e.preventDefault();

      $(this).parent().addClass('active');
      $(this).parent().siblings().removeClass('active');

      target = $(this).attr('href');

      $('.tab-content > div').not(target).hide();

      $(target).fadeIn(300);

    });
});