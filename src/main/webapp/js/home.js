$(document).ready(function() {

    var $techNews = $("#techNews"),
        $universityNews = $("#universityNews"),
        $articleManager = $("#articleManager"),
        articleData = null;

    $.getJSON("rest/article", function(data) {
        articleData = data.article;

        for (var i = 0; i < articleData.length; i++) {
            var articleItem = articleData[i];
            var date = articleItem.postDate.substring(0, 10);
            if (articleItem.articleType == "TechNews") {
                $techNews.append('<article><h2 class="articleHeading">' + articleItem.title + '</h2><p class="author">posted on ' + date + '</p><p class="articleContent">' + articleItem.text + '</p></article>');
            }
            else {
                $universityNews.append('<article><h2 class="articleHeading">' + articleItem.title + '</h2><p class="author">posted on ' + date + '</p><p class="articleContent">' + articleItem.text + '</p></article>');
            }
            $articleManager.append('<tr><td>' + articleItem.title + '</td><td>' + date + '</td><td><a href="#" class="btn btn-warning" role="button">Edit</a></td><td><a href="#" class="btn btn-danger" role="button">Delete</a></td>');
        }
    });

    $('nav a').click(function() {
        var tab_id = $(this).attr('data-tab');

        $('.tab-content').removeClass('current');

        $("." + tab_id).addClass('current');
    });


    var $AddButton = $("#add-button");
    $AddButton.click(function() {
        var oArticleData = {
            article: {
                title: $("#article-title").val(),
                text: $("#article-body").val()
            }
        };

        console.log(JSON.stringify(oArticleData));
        $.ajax({
                url: 'rest/article/add',
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(oArticleData)
            })
            .success(function(data) {
                alert("Added successfully!");
            })
            .fail(function(data) {
                alert("Failed to add article!");
            })
            .always(function() {
                $("#add-form").submit();
            });
    });

    $('#logout-button').click(function() {
        $.ajax({
            url: 'rest/user/logout',
            type: "GET",
            statusCode: {
                204: function() {
                    alert("Успешно излизане от системата!");
                    window.location.replace("index.html");
                }
            }
        });
    });
});
