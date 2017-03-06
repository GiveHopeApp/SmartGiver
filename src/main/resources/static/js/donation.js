/**
 * Created by David on 2/28/17.
 */
"use strict";
(function () {

    // Call Panda.init() with your Panda Publishable Key and the DOM id of the
    // credit card-related form element
    Panda.init('pk_test_wsQoLJzX6-yUXv1gGcUgvQ', 'panda_cc_form');

    //Event listener for donate button
    $("#donate_btn").click(function() {

        $(".overlay").fadeIn();

        Panda.on('success', function(cardToken) {

            $(".overlay").fadeOut();
            // You now have a token you can use to refer to that credit card later.
            // This token is used in PandaPay API calls for creating donations and grants
            // so that you don't have to worry about security concerns with dealing with
            // credit card data.
            console.log('success');

            $("#hiddenForm").attr("action", "/donate/confirm/" + cardToken);
            $("#hiddenEmail").val($("#visibleEmail").val());

            $("#hiddenForm").submit();
        });

        Panda.on('error', function(errors) {

            $(".overlay").fadeOut();
            // errors is a human-readable list of things that went wrong
            //  (invalid card number, missing last name, etc.)
            console.log(errors);


            // var error;
            // for (var i = 0; i <= 6; i++) {
            //     error = errors[i];
            //     if (error[0] == "month") {
            //         console.log('test')
            //     }
            // }
        });
    })

})();
