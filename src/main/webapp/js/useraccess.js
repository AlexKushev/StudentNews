$(document).ready(function() {
    var loginButton = $('#login-button'),
        registerButton = $('#register-button'),
        userData = null;

    // CHECK IF LOGGED IN
    $.getJSON('rest/user/current', function(data) {
        userData = data.user;
        if (userData.userName != null) {
            window.location.href = 'home.html';
        }
    });

    loginButton.on('click', function() {
        login();
    });

    $('#login-password').keydown(function(e) {
        if (e.keyCode == 13) {
            login();
        }
    });

    registerButton.click(function() {
        var registerData = {
            user: {
                firstName: $('#register-firstName').val(),
                lastName: $('#register-lastName').val(),
                userName: $('#register-userName').val(),
                password: $('#register-password').val()
            }
        };

        // Validates user input
        if (!validate()) {
            return;
        }

        $.ajax({
                url: 'rest/user/register',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(registerData)
            })
            .success(function() {
                alert('Register success! You can now login in the system!');
                window.location = 'index.html';
            })
            .fail(function() {
                alert('Invalid data or user with this data already exists!');
            })
            .always(function() {
                $('#register-form').submit(function() {
                    var form = this;
                    setTimeout(function() {
                        $(':submit', form).attr('disabled', true);
                    }, 50);
                });
            });
    });

    function login() {
        var userInput = {
            user: {
                userName: $('#login-userName').val(),
                password: $('#login-password').val()
            }
        };

        $.ajax({
            url: 'rest/user/login',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(userInput),
            statusCode: {
                401: function() {
                    alert('Wrong username or password!');
                },
                200: function() {

                    $.ajax({
                        type: 'GET',
                        complete: function() {
                            window.location.replace('home.html');
                        }
                    });
                }
            }
        });
    }

    function validate() {
        var firstName = $('#register-firstName'),
            lastName = $('#register-lastName'),
            userName = $('#register-userName'),
            password = $('#register-password'),
            passwordRe = $('#register-password-re');

        if (firstName.val().length < 2 || lastName.val().length < 2 || userName.val().length < 2) {
            alert('Invalid data! First Name, Last Name and Username must consist of at least 2 symbols!');
            return false;
        } else if (firstName.val().length > 15 || lastName.val().length > 15 || userName.val().length > 15) {
            alert('Invalid data! First Name, Last Name and Username must consist of no more than 15 symbols!');
        }

        if (password.val().length < 5 || password.val().length > 15) {
            alert('Password length must have more than 5 and less than 15 symbols!');
            return false;
        }

        if (password.val() !== passwordRe.val()) {
            alert('Passwords do not match!');
            return false;
        }

        return true;
    }
});
