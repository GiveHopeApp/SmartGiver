/**
 * Created by David on 2/25/17.
 */
"use strict";
(function () {

    //Global variables
    var html = "";

    //The event listener for the search bar
    $("#searchTerm").keyup(function () {

        //Makes the search term the path variable for use by the controller
        var url = "/" + $(this).val() + ".json";

        //The ajax request to the search controller
        var request = $.ajax ({
            url: url
        });

        //Generates the html for the results
        request.done(function (charities) {

            html = "";

            charities.forEach(function (charity) {

                html += "<p>"
                    + "<a href='/charities/" + charity.id + "'>"
                    + charity.charityName + "</a> "
                    + charity.category + "</p>";
            });

            if (charities.length == 0) {
                html = "We didn't find anything..."
            }

            $("#searchResults").html(html);
        });

        //Clears the html if there is a failure
        request.fail(function () {

            html = "";

            $("#searchResults").html(html);
        });

    });

})();
