CREATE TABLE [PasswordReset]
(
	PassId INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	Email NVARCHAR(50) NOT NULL,
	ResetToken NVARCHAR(50)
)

CREATE TABLE OldPassword
(
	OldpassId INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	UserId INT NOT NULL,
	OldPassword NVARCHAR (100)
)