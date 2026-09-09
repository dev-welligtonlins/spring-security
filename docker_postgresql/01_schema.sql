

-- USERS
CREATE TABLE IF NOT EXISTS users (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    user_login TEXT UNIQUE NOT NULL,
    user_password TEXT NOT NULL,
    user_role TEXT NOT NULL
);

-- CLIENTES
CREATE TABLE IF NOT EXISTS clients (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    login TEXT UNIQUE NOT NULL,
    name TEXT NOT NULL,
    phone TEXT NOT NULL,
    user_id TEXT NOT NULL,

    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES users(id) ON DELETE CASCADE
);

-- BARBEARIAS
CREATE TABLE IF NOT EXISTS barbershops (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    login TEXT UNIQUE NOT NULL,
    name TEXT NOT NULL,
    phone TEXT NOT NULL,
    user_id TEXT NOT NULL,
    
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES users(id) ON DELETE CASCADE
);

-- HORÁRIOS (DIA, ABERTURA, INTERVALO E FECHAMENTO)
CREATE TABLE IF NOT EXISTS barbershop_schedules (
	id TEXT PRIMARY KEY UNIQUE NOT NULL,
	day_week VARCHAR(15) NOT NULL,
	opening_time TIME NOT NULL,
	lunch_start_time TIME,
	lunch_end_time TIME,
	closing_time TIME NOT NULL,
	barbershop_id TEXT NOT NULL,

	CONSTRAINT fk_barbershop FOREIGN KEY (barbershop_id)
		REFERENCES barbershops(id) ON DELETE CASCADE
);

-- ENDEREÇOS 
CREATE TABLE IF NOT EXISTS address (
	id TEXT PRIMARY KEY UNIQUE NOT NULL,
	cep VARCHAR(9) NOT NULL,
	road VARCHAR(80) NOT NULL,
	neighborhood VARCHAR(50) NOT NULL,
	address_number INT NOT NULL,
	complement VARCHAR(50),
	city VARCHAR(40),
	address_state VARCHAR(2),
	barbershop_id TEXT NOT NULL,

	CONSTRAINT fk_barbershop FOREIGN KEY (barbershop_id)
		REFERENCES barbershops(id) ON DELETE CASCADE
);

-- ACOMODAÇÕES DA BARBEARIA
CREATE TABLE IF NOT EXISTS accommodations (
	id TEXT PRIMARY KEY UNIQUE NOT NULL,
	wifi BOOLEAN NOT NULL,
	parking BOOLEAN NOT NULL,
	bath BOOLEAN NOT NULL,
	air_conditioner BOOLEAN NOT NULL,
	barbershop_id TEXT NOT NULL,

	CONSTRAINT fk_barbershop
		FOREIGN KEY (barbershop_id)
		REFERENCES barbershops(id)
		ON DELETE CASCADE
);

-- BARBEIROS
CREATE TABLE IF NOT EXISTS barbers (
	id TEXT PRIMARY KEY UNIQUE NOT NULL,
	barber_name VARCHAR(45) NOT NULL,
	url_social VARCHAR(120),
	is_hair BOOLEAN NOT NULL,
	is_beard BOOLEAN NOT NULL,
	barber_active BOOLEAN NOT NULL,
	barbershop_id TEXT NOT NULL,
	
	CONSTRAINT fk_barbershop FOREIGN KEY (barbershop_id)
		REFERENCES barbershops(id) ON DELETE CASCADE
);



-- SERVIÇOS
CREATE TABLE IF NOT EXISTS services (
	id TEXT PRIMARY KEY UNIQUE NOT NULL,
	service_description VARCHAR(45) NOT NULL,
	duration INT NOT NULL,
	service_value FLOAT NOT NULL,
	category VARCHAR(45) NOT NULL,
	service_active BOOLEAN NOT NULL,
	barbershop_id TEXT NOT NULL,
	
	CONSTRAINT fk_barbershop FOREIGN KEY (barbershop_id)
		REFERENCES barbershops(id) ON DELETE CASCADE
);

-- AGENDAMENTO DO CLIENTE E HORÁRIO
CREATE TABLE IF NOT EXISTS appointments (
	id TEXT PRIMARY KEY UNIQUE NOT NULL,
	client_name VARCHAR(100),
	appointment_time TIMESTAMP NOT NULL,
	appointment_status VARCHAR(25) NOT NULL,
	value_total FLOAT NOT NULL,
	barbershop_id TEXT NOT NULL,

	CONSTRAINT fk_barbershop FOREIGN KEY (barbershop_id)
		REFERENCES barbershops(id) ON DELETE CASCADE
);

-- ITENS DE UM AGENDAMENTO
CREATE TABLE IF NOT EXISTS service_appointments (
	id TEXT PRIMARY KEY UNIQUE NOT NULL,
	service_time TIME NOT NULL,
	barber_id TEXT,
	service_id TEXT,
	appointment_id TEXT NOT NULL,
	
	CONSTRAINT fk_barber FOREIGN KEY (barber_id)
		REFERENCES barbers(id) ON DELETE CASCADE,
		
	CONSTRAINT fk_service FOREIGN KEY (service_id)
		REFERENCES services(id) ON DELETE CASCADE,

	CONSTRAINT fk_appointment FOREIGN KEY (appointment_id)
		REFERENCES appointments(id) ON DELETE CASCADE
);


-- PAGAMENTOS
CREATE TABLE IF NOT EXISTS payments (
	id TEXT PRIMARY KEY UNIQUE NOT NULL,
	appointment_id TEXT NOT NULL,
	payment_value FLOAT NOT NULL,
	payment_method VARCHAR(15) NOT NULL,
	payment_status BOOLEAN NOT NULL,

	CONSTRAINT fk_appointment FOREIGN KEY (appointment_id)
		REFERENCES appointments(id) ON DELETE CASCADE
);