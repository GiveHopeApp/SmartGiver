/**
 * Created by rubenvarela on 2/28/17.
 */
$(document).ready(function () {
    $('.donate-wrapper > button').click(function() {
        $('.donate-wrapper > button').removeClass('active');
        $(this).addClass('active')
    });
});