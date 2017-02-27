/**
 * Created by David on 2/25/17.
 */
"use strict";
(function () {

    $("#searchTerm").keyup(function () {

        var url = "/" + $(this).val() + ".json";

        var request = $.ajax ({
            url: url
        });

        request.done(function (charities) {

            var html = "";

            console.log(charities);

            charities.forEach(function (charity) {
                html += "<p>"
                    + charity.charityName + " "
                    + charity.category + " "
                    + charity.ein + " "
                    + charity.city + " "
                    + charity.state + " "
                    + charity.description + "</p>";
            });

            if (charities.length == 0) {
                html = "We didn't find anything..."
            }

            $("#searchResults").html(html);
        });

        request.fail(function () {

            var html = "";

            $("#searchResults").html(html);
        });

    });

})();
