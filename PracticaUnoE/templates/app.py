from flask import Flask

def create_app():
    app = Flask(__name__, instance_relative_config=False)
    app.config.from_object("config.config.Config")

    with app.app_context():
        from routes.router import router
        app.register_blueprint(router)

    return app

if __name__ == "__main__":
    app = create_app()
    app.run(debug=True)  # Cambia a False en producción
