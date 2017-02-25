/**
 * Created by David on 2/25/17.
 */
"use strict";
(function () {

    $("#searchTerm").keyup(function () {
        var request = $.ajax ({
            url: "/charities.json"
        });

        request.done(function (charities) {

            console.log("done");

            var html = "";

            console.log(charities);

            charities.forEach(function (charity) {
                html += "<p>"
                    + charity.charityName + " "
                    + charity.category + " "
                    + charity.ein + " "
                    + charity.state + " "
                    + charity.description + "</p>";
            });

            $("#searchResults").html(html);
        })
    });


})();
