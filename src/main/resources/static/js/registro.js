document.getElementById('registroForm').addEventListener('submit', async function(e) {
      e.preventDefault();
      const form = e.target;
      let valid = true;

      if (!form.checkValidity()) {
        form.classList.add('was-validated');
        valid = false;
      }

      const pass = document.getElementById('contrasena');
      const pass2 = document.getElementById('contrasenaRepeat');
      if (pass.value !== pass2.value) {
        pass2.classList.add('is-invalid');
        document.getElementById('contrasenaRepeatFeedback').textContent = 'Las contraseñas no coinciden.';
        valid = false;
      } else {
        pass2.classList.remove('is-invalid');
      }

      const usuarioInput = document.getElementById('nombreUsuario');
      const usuarioFeedback = document.getElementById('usuarioFeedback');
      try {
        const resp = await fetch('/usuario/checkUsername?nombreUsuario=' + encodeURIComponent(usuarioInput.value));
        const data = await resp.json();
        if (data.exists) {
          usuarioInput.classList.add('is-invalid');
          usuarioFeedback.textContent = 'Este nombre de usuario ya está en uso.';
          valid = false;
        } else {
          usuarioInput.classList.remove('is-invalid');
        }
      } catch (err) {
        console.error('Error al verificar usuario:', err);
      }

      if (valid) {
        form.submit();
      }
    });