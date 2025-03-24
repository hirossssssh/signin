<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StreamVibe - Login</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(to bottom, #6900a7, #000000);
            color: #fff;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            transition: background 0.3s ease;
        }

        .login-container {
            background: #1a1a1a;
            padding: 40px;
            border-radius: 15px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.5);
            transition: all 0.3s ease;
        }

        .login-container:hover {
            box-shadow: 0 6px 25px rgba(106, 13, 173, 0.3);
        }

        .logo {
            font-size: 32px;
            font-weight: bold;
            text-align: center;
            margin-bottom: 30px;
            color: #fff;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            font-size: 14px;
            color: #b3b3b3;
            margin-bottom: 8px;
        }

        .form-group input {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 8px;
            background: #2a2a2a;
            color: #fff;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        .form-group input:focus {
            outline: none;
            background: #3a3a3a;
            box-shadow: 0 0 5px rgba(106, 13, 173, 0.5);
        }

        .login-btn {
            width: 100%;
            padding: 12px;
            background: #6a0dad;
            border: none;
            border-radius: 8px;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .login-btn:hover {
            background: #4b0082;
            transform: translateY(-2px);
        }

        .signup-link {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
            color: #b3b3b3;
        }

        .signup-link a {
            color: #6a0dad;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .signup-link a:hover {
            color: #4b0082;
        }

        .error-message {
            color: #ff4444;
            font-size: 14px;
            text-align: center;
            margin-top: 10px;
            display: none;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="logo">StreamVibe</div>
        <form id="login-form" onsubmit="handleLogin(event)">
            <div class="form-group">
                <label for="username">Username or Email</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="login-btn">Log In</button>
            <div class="error-message" id="error-message">Invalid credentials</div>
            <div class="signup-link">
                Don't have an account? <a href="#" onclick="alert('Sign up functionality coming soon!')">Sign up</a>
            </div>
        </form>
    </div>

    <script>
        function handleLogin(event) {
            event.preventDefault();
            
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            const errorMessage = document.getElementById('error-message');

            fetch('login.php', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
            })
            .then(response => response.json())
            .then(data => {
                if (data.status === "success") {
                    window.location.href = 'index.html'; // Redirect to index.html on success
                } else {
                    errorMessage.style.display = 'block';
                    errorMessage.textContent = data.message;
                    setTimeout(() => { errorMessage.style.display = 'none'; }, 3000);
                }
            })
            .catch(error => console.error('Error:', error));
        }
    </script>
</body>
</html>
