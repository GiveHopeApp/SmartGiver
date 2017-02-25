/**
 * Created by David on 2/25/17.
 */
"use strict";
(function () {

    var request = $.ajax ({
        url: "/charities.json"
    });

    request.done(function (charities) {

        var html = "";

        console.log(charities);

        charities.forEach(function (charity) {
            html += "<p>"
                + charity.charityName + " "
                + charity.category + " "
                + charity.ein + "</p>";
        });

        $("#searchResults").html(html);
    })
})();
