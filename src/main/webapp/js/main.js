$(document).ready(function() {
    var $LoginButton = $("#login-button"),
        $RegisterButton = $("#register-button");

    $LoginButton.on("click", function() {
        login();
    });

    $('#login-password').keydown(function(e) {
        if (e.keyCode == 13) {
            login();
        }
    });

    $RegisterButton.click(function() {
        var registerData = {
            user: {
                firstName: $("#register-firstName").val(),
                lastName: $("#register-lastName").val(),
                userName: $("#register-userName").val(),
                password: $("#register-password").val()
            }
        };

        // Validates user input
        if (!validate()) {
            return;
        }

        $.ajax({
                url: 'rest/user/register',
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(registerData)
            })
            .success(function() {
                alert("Register success! You can now login in the system!");
                window.location = "index.html";
            })
            .fail(function() {
                alert("Invalid data or user with this data already exists!");
            })
            .always(function() {
                $("#register-form").submit(function() {
                    var form = this;
                    setTimeout(function() {
                        $(':submit', form).attr('disabled', true);
                    }, 50);
                });
            });
    });

    function login() {
        var userData = {
            user: {
                userName: $("#login-userName").val(),
                password: $("#login-password").val()
            }
        };

        $.ajax({
            url: 'rest/user/login',
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(userData),
            statusCode: {
                401: function() {
                    alert("Wrong username or password!");
                },
                200: function() {

                    $.ajax({
                        type: "GET",
                        complete: function() {
                            window.location.replace("home.html");
                        }
                    });
                }
            }
        });
    }

    function validate() {
        var $FirstName = $('#register-firstName'),
            $LastName = $('#register-lastName'),
            $userName = $('#register-userName'),
            $Password = $('#register-password'),
            $RePassword = $('#register-password-re');

        if ($FirstName.val().length < 2 || $LastName.val().length < 2 || $userName.val().length < 2) {
            alert('Invalid data! First Name, Last Name and Username must consist of at least 2 symbols!');
            return false;
        } else if ($FirstName.val().length > 15 || $LastName.val().length > 15 || $userName.val().length > 15) {
            alert('Invalid data! First Name, Last Name and Username must consist of no more than 15 symbols!');
        }

        if ($Password.val().length < 5 || $Password.val().length > 15) {
            alert('Password length must have more than 5 and less than 15 symbols!');
            return false;
        }

        if ($Password.val() !== $RePassword.val()) {
            alert('Passwords do not match!');
            return false;
        }

        return true;
    }
});
