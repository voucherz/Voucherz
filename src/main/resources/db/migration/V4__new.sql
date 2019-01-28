CREATE TABLE [dbo].[PasswordReset]
(
	PassId INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
	Email NVARCHAR(50) NOT NULL,
	ResetToken NVARCHAR(50) NOT NULL,
	ExpiryDate Date NOT NULL,
)

