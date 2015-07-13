package com.exadel.training.common;

/**
 * Created by HP on 12.07.2015.
 */
public enum RoleType {
    Admin,
    Employee,
    ExCoache,
    ExEmployee;

    public RoleType parseIntToRoleType(int type) throws NoSuchFieldException {
        switch (type) {
            case 1: {
                return RoleType.Admin;
            }
            case 2: {
                return  RoleType.Employee;
            }
            case 3: {
                return  RoleType.ExCoache;
            }
            case  4: {
                return  RoleType.ExEmployee;
            }
            default: {
                throw new NoSuchFieldException("don't find such type.");
            }
        }
    }
    public int parseRoleTypeToInt(RoleType type) throws NoSuchFieldException    {
        switch (type) {
                case Admin: {
                    return 1;
                }
                case Employee: {
                    return 2;
                }
                case ExCoache: {
                    return 3;
                }
                case ExEmployee: {
                    return 4;
                }
                default: {
                    throw new NoSuchFieldException("don't find such type.");
                }
            }
        }
}
